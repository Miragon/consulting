package io.miragon.miranum.examples.com.adapter.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MailCommand {

    private String name;
    private String email;
    private String content;
}