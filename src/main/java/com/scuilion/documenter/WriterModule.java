package com.scuilion.documenter;

import com.google.inject.AbstractModule;

public class WriterModule extends AbstractModule {
  @Override 
  protected void configure() {
    bind(Writer.class).to(DefaultWriter.class);
  }
}