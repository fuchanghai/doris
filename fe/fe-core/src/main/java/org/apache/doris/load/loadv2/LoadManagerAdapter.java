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

package org.apache.doris.load.loadv2;

import org.apache.doris.analysis.InsertStmt;
import org.apache.doris.analysis.LoadType;
import org.apache.doris.catalog.Env;
import org.apache.doris.common.DdlException;
import org.apache.doris.qe.StmtExecutor;

/**
 * This class is temporary for load refactor, all unified external load should use this adapter in
 * {@link StmtExecutor#handleExternalInsertStmt()}
 * <p>
 * TODO(tsy): removed after job-manager system for loads is unified
 */
public class LoadManagerAdapter {

    public void startLoadFromInsertStmt(InsertStmt insertStmt) throws DdlException {
        final LoadType loadType = insertStmt.getLoadType();
        final LoadManager loadManager = Env.getCurrentEnv().getLoadManager();
        switch (loadType) {
            case BROKER_LOAD:
                loadManager.createLoadJobFromStmt(insertStmt);
                break;
            case MYSQL_LOAD:
                // TODO: implement
                break;
            case ROUTINE_LOAD:
                // TODO: implement
                break;
            case STREAM_LOAD:
                // TODO: implement
                break;
            default:
                throw new DdlException("unsupported load type:" + loadType);
        }
    }

}
