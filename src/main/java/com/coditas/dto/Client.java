package com.coditas.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter @Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "client")
public class Client {
    @NonNull private int clientId;
    private String clientName;
    private String clientEmail;
    private String contact;
}
