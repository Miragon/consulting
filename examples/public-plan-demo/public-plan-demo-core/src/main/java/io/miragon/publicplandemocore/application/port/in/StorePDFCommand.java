package io.miragon.publicplandemocore.application.port.in;

public class StorePDFCommand {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class StorePDFCommand {

        private byte[] bytes;
    }

}
