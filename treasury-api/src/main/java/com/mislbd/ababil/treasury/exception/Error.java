package com.mislbd.ababil.treasury.exception;

import com.mislbd.ababil.asset.service.LocaleMessages;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public enum Error {
  // product region
  PRODUCT_NOT_FOUND("270", "0001") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message = localeMessages.get(this.getModule() + this.getCode()).orElse("Product not found");
      return message;
    }
  },

  TRANSACTION_RECORD_NOT_FOUND("270", "0002") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message = localeMessages.get(this.getModule() + this.getCode()).orElse("Product not found");
      return message;
    }
  },

  PRODUCT_NATURE_NOT_FOUND("270", "0003") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("Product Nature not found");
      return message;
    }
  },

  ACCOUNT_NOT_FOUND("270", "0004") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("Product Nature not found");
      return message;
    }
  },

  PRODUCT_RELATED_GL_NOT_FOUND("270", "0005") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("Product Nature not found");
      return message;
    }
  },

  PROVISION_MISMATCH("270", "0006") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages.get(this.getModule() + this.getCode()).orElse("Product Nature not found");
      return message;
    }
  },

  GENERAL_LEDGER_ACCOUNT_NOT_FOUND("270", "0007") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages
              .get(this.getModule() + this.getCode())
              .orElse("General ledger account not found.");
      return message;
    }
  },

  REACTIVE_TRANSACTION_EXCEPTION("270", "0007") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
          localeMessages
              .get(this.getModule() + this.getCode())
              .orElse("Reactive transaction exception");
      return message;
    }
  },

  PROCESS_RECORD_NOT_FOUND("270", "0008") {
    private String message;

    @Override
    public String getMessages() {
      return message;
    }

    @Override
    public String getMessages(LocaleMessages localeMessages) {
      message =
              localeMessages.get(this.getModule() + this.getCode()).orElse("Rrecord not found");
      return message;
    }
  },
  ;

  // region <R>
  Error(final String module, final String code) {
    this.code = code;
    this.module = module;
  }

  private String code;
  private String module;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  // Lookup table
  private static final Map<String, Error> lookup = new HashMap<>();

  // Populate the lookup table on loading time
  static {
    EnumSet.allOf(Error.class).forEach(r -> lookup.put(r.getCode(), r));
  }

  // Reverse lookup
  public static Error get(String code) {
    return lookup.get(code);
  }

  public abstract String getMessages();

  public abstract String getMessages(LocaleMessages localeMessages);

  @Component
  public static class TreasuryLocaleMessagesInjector {

    @Autowired private final LocaleMessages localeMessages;

    public TreasuryLocaleMessagesInjector(LocaleMessages localeMessages) {
      this.localeMessages = localeMessages;
      for (Error error : EnumSet.allOf(Error.class)) error.getMessages(localeMessages);
    }
  }
  // endregion

}
