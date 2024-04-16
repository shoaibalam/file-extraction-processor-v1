package com.dsv.datafactory.file.extraction.processor.models;

import com.dsv.datafactory.model.MetaData;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@RequiredArgsConstructor
public class ErrorMessage implements Serializable
{
    @Getter @Setter @NonNull private String topicKey;
    @Getter @Setter @NonNull private MetaData topicMessage;
    @Getter @Setter @NonNull private Throwable exception;
}
