package app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    @Id
    private String id;
    @NonNull
    @Indexed(unique=true)
    private String name;
    @NonNull
    private String type;
    @NonNull
    private long createTime;
    @NonNull
    private String createdBy;

    @PersistenceConstructor
    @JsonCreator
    public Organization(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            String createdBy) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdBy = createdBy;
        this.createTime = System.currentTimeMillis();
    }

    // required to save as DBRef
    public Organization(String id){
        this.id = id;
    }
}
