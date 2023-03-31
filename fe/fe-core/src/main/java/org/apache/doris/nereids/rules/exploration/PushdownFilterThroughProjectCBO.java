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

package org.apache.doris.nereids.rules.exploration;

import org.apache.doris.nereids.rules.Rule;
import org.apache.doris.nereids.rules.RuleType;
import org.apache.doris.nereids.rules.rewrite.logical.PushdownFilterThroughProject;

/**
 * Push down filter through project.
 * input:
 * filter(a>2, b=0) -> project(c+d as a, e as b)
 * output:
 * project(c+d as a, e as b) -> filter(c+d>2, e=0).
 */
public class PushdownFilterThroughProjectCBO extends OneExplorationRuleFactory {
    @Override
    public Rule build() {
        return logicalFilter(logicalProject())
                .then(PushdownFilterThroughProject::pushdownFilterThroughProject)
                .toRule(RuleType.PUSHDOWN_FILTER_THROUGH_PROJECT);
    }
}
