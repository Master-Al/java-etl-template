package com.sdge.si.graphql.etl.domain.port;

import com.sdge.si.graphql.etl.domain.model.EtlRecord;
import com.sdge.si.graphql.etl.domain.model.EtlRequest;

public interface Extractor {
    EtlRecord extract(EtlRequest request);
}
