package app.dto;

import lombok.Data;

@Data
public class CreateOrgRequest {
    private String name;
    private String type;
}
