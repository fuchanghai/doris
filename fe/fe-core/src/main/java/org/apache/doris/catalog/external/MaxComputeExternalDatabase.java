// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.catalog.external;

import org.apache.doris.datasource.ExternalCatalog;
import org.apache.doris.datasource.InitDatabaseLog;
import org.apache.doris.datasource.MaxComputeExternalCatalog;
import org.apache.doris.persist.gson.GsonPostProcessable;

/**
 * MaxCompute external database.
 */
public class MaxComputeExternalDatabase extends ExternalDatabase<MaxComputeExternalTable>
            implements GsonPostProcessable {
    /**
     * Create MaxCompute external database.
     *
     * @param extCatalog External catalog this database belongs to.
     * @param id database id.
     * @param name database name.
     */
    public MaxComputeExternalDatabase(ExternalCatalog extCatalog, long id, String name) {
        super(extCatalog, id, name, InitDatabaseLog.Type.MAX_COMPUTE);
    }

    @Override
    protected MaxComputeExternalTable getExternalTable(String tableName, long tblId, ExternalCatalog catalog) {
        return new MaxComputeExternalTable(tblId, tableName, name, (MaxComputeExternalCatalog) extCatalog);
    }
}
