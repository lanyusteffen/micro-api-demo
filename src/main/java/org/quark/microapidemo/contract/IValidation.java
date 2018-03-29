package org.quark.microapidemo.contract;

import org.quark.microapidemo.exception.DomainException;

public interface IValidation {
    void validation() throws DomainException;
}
