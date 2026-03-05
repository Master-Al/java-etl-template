package com.sdge.si.graphql.etl.domain.port;

import com.sdge.si.graphql.etl.domain.model.EtlLoadResult;
import com.sdge.si.graphql.etl.domain.model.EtlTransformedRecord;

public interface Loader {
    EtlLoadResult load(EtlTransformedRecord record);
}
