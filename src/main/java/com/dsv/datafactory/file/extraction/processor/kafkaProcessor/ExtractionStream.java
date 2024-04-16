package com.dsv.datafactory.file.extraction.processor.kafkaProcessor;

import com.dsv.datafactory.file.extraction.processor.domain.*;
import com.dsv.datafactory.model.MetaData;
import com.dsv.datafactory.serde.MetaDataSerde;
import com.google.inject.Inject;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ExtractionStream {
	@Autowired
	private ExtractContent extractDocument;
	@Autowired
	kafkaTemplate<String, MetaData> kafkaTemple;

	@Bean
	public Consumer<Message<String, MetaData>> processFileExtration() {
		return metaData -> ExtractionStream.createFrom(metaData);
	}

	private static void createFrom(MetaData stream) {

		KStream<String, MetaData> documentExtractions = stream.mapValues(extractDocument::execute);
		KStream<String, MetaData> documentExtractionsFiltered = documentExtractions.filter((k, v) -> v != null);

		CompletableFuture<SendResult<String, MetaData>> future = this.kafkaTemple.send("topic_name", UUID.toString(), documentExtractionsFiltered);
		try{
			future.get();
			this.kafkaTemple.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
