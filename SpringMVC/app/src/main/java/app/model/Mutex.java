package app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mutex")
@Data
public class Mutex {
    @Id
    private String id;

    private String hostName;

    private long updateTime;
}
