package com.keytracker;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.storing.StoreMechanism;

import java.util.List;

public interface MechanismsFactory {
    StoreMechanism getBundledStoreMechanism(ValueProvider valueProvider, Encryptor encryptor);

    List<StoreMechanism> getSingleStoreMechanisms(ValueProvider valueProvider, Encryptor encryptor);
}
