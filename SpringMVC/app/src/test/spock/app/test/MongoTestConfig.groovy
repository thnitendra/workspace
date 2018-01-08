package app.test

import com.mongodb.Mongo
import com.mongodb.MongoClient
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder
import cz.jirutka.spring.embedmongo.slf4j.Slf4jLevel
import cz.jirutka.spring.embedmongo.slf4j.Slf4jProgressListener
import cz.jirutka.spring.embedmongo.slf4j.Slf4jStreamProcessor
import de.flapdoodle.embed.mongo.Command
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodProcess
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.*
import de.flapdoodle.embed.mongo.distribution.IFeatureAwareVersion
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.mongo.distribution.Versions
import de.flapdoodle.embed.process.config.IRuntimeConfig
import de.flapdoodle.embed.process.config.io.ProcessOutput
import de.flapdoodle.embed.process.config.store.HttpProxyFactory
import de.flapdoodle.embed.process.distribution.GenericVersion
import de.flapdoodle.embed.process.runtime.Network
import de.flapdoodle.embed.process.store.Downloader
import de.flapdoodle.embed.process.store.IArtifactStore
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import static de.flapdoodle.embed.mongo.distribution.Version.Main.PRODUCTION

@Configuration
class MongoTestConfig extends EmbeddedMongoBuilder {
    private static final LOG = LoggerFactory.getLogger(MongoTestConfig.class);

    private IFeatureAwareVersion version = PRODUCTION;
    private Integer port;
    private String bindIp = InetAddress.getLoopbackAddress().getHostAddress();

    @Bean
    public Mongo mongo() throws IOException {
        MongoTestConfig builder = new MongoTestConfig();
        return builder.port(12345).bindIp("127.0.0.1").version("2.4.5").build()
    }

    @Override
    public MongoClient build() throws IOException {
        LOG.info("Initializing embedded MongoDB instance");
        MongodStarter runtime = MongodStarter.getInstance(buildRuntimeConfig());
        MongodExecutable mongodExe = runtime.prepare(buildMongodConfig());

        LOG.info("Starting embedded MongoDB instance");
        mongodExe.start();

        return new MongoClient(bindIp, getPort());
    }


    public EmbeddedMongoBuilder version(Version version) {
        if (version == null) {
            throw new IllegalArgumentException("Version must not be null");
        }
        this.version = version;
        return this;
    }

    public EmbeddedMongoBuilder version(String version) {
        if (version == null || version.isEmpty()) {
            throw new IllegalArgumentException("Version must not be null or empty");
        }
        this.version = parseVersion(version);
        return this;
    }

    public EmbeddedMongoBuilder port(int port) {
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Port number must be between 0 and 65535");
        }
        this.port = port;
        return this;
    }

    public EmbeddedMongoBuilder bindIp(String bindIp) {
        if (bindIp == null || bindIp.isEmpty()) {
            throw new IllegalArgumentException("BindIp must not be null or empty");
        }
        this.bindIp = bindIp;
        return this;
    }


    private int getPort() {
        if (port == null) {
            try {
                port = Network.getFreeServerPort();
            } catch (IOException ex) {
                LOG.error("Could not get free server port");
            }
        }
        return port;
    }

    private ProcessOutput buildOutputConfig() {
        Logger logger = LoggerFactory.getLogger(MongodProcess.class);

        return new ProcessOutput(
                new Slf4jStreamProcessor(logger, Slf4jLevel.TRACE),
                new Slf4jStreamProcessor(logger, Slf4jLevel.WARN),
                new Slf4jStreamProcessor(logger, Slf4jLevel.INFO));
    }

    private IRuntimeConfig buildRuntimeConfig() {
        Command command = Command.MongoD
        return new RuntimeConfigBuilder()
                .defaults(command)
                .artifactStore(new ArtifactStoreBuilder()
                    .defaults(command)
                    .download(new DownloadConfigBuilder()
                        .defaultsForCommand(command)
                        //.proxyFactory(new HttpProxyFactory("proxyhost", proxyport))
                ))
                .build();
    }

    private IArtifactStore buildArtifactStore() {
        Logger logger = LoggerFactory.getLogger(Downloader.class);

        return new ArtifactStoreBuilder()
                .defaults(Command.MongoD)
                .download(new DownloadConfigBuilder()
                .defaultsForCommand(Command.MongoD)
                .progressListener(new Slf4jProgressListener(logger))
                .build())
                .build();
    }

    private IMongodConfig buildMongodConfig() throws IOException {
        return new MongodConfigBuilder()
                .version(version)
                .net(new Net(bindIp, getPort(), Network.localhostIsIPv6()))
                .build();
    }

    private IFeatureAwareVersion parseVersion(String version) {
        String versionEnumName = version.toUpperCase().replaceAll("\\.", "_");
        if (!versionEnumName.startsWith("V")) {
            versionEnumName = "V" + versionEnumName;
        }
        try {
            return Version.valueOf(versionEnumName);
        } catch (IllegalArgumentException ex) {
            LOG.warn("Unrecognised MongoDB version '{}', this might be a new version that we don't yet know about. " +
                    "Attempting download anyway...", version);
            return Versions.withFeatures(new GenericVersion(version));
        }
    }
}
