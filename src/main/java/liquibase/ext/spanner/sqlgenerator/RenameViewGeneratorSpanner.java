/**
 * Copyright 2020 Google LLC
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>https://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package liquibase.ext.spanner.sqlgenerator;

import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.ext.spanner.CloudSpanner;
import liquibase.ext.spanner.ICloudSpanner;
import liquibase.sqlgenerator.SqlGenerator;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.RenameViewGenerator;
import liquibase.statement.core.RenameViewStatement;

public class RenameViewGeneratorSpanner extends RenameViewGenerator {
  static final String RENAME_VIEW_VALIDATION_ERROR =
      "Cloud Spanner does not support renaming views";

  @Override
  public ValidationErrors validate(
      RenameViewStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
    ValidationErrors errors = super.validate(statement, database, sqlGeneratorChain);
    errors.addError(RENAME_VIEW_VALIDATION_ERROR);
    return errors;
  }

  @Override
  public int getPriority() {
    return SqlGenerator.PRIORITY_DATABASE;
  }

  @Override
  public boolean supports(RenameViewStatement statement, Database database) {
    return (database instanceof ICloudSpanner);
  }
}
