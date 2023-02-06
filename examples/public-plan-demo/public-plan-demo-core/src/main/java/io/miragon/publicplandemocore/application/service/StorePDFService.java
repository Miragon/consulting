package io.miragon.publicplandemocore.application.service;

import io.miragon.publicplandemocore.application.port.in.StorePDFCommand;
import io.miragon.publicplandemocore.application.port.in.StorePDFUseCase;
import io.miragon.publicplandemocore.application.port.out.StorePDFPort;

@AllArgsConstructor
public class StorePDFService implements StorePDFUseCase {

    private final StorePDFPort storePDFPort;

    @Override
    @Worker(type="storePDF")
    public void storePDF(StorePDFCommand storePDFCommand) {
        storePDFPort.storePDF(storePDFCommand.getBytes());
    }
}

