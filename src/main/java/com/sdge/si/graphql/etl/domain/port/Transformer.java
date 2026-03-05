package com.sdge.si.graphql.etl.domain.port;

import com.sdge.si.graphql.etl.domain.model.EtlRecord;
import com.sdge.si.graphql.etl.domain.model.EtlTransformedRecord;

public interface Transformer {
    EtlTransformedRecord transform(EtlRecord record);
}
