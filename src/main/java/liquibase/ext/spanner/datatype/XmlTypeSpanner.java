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
 * limitations under the License. package liquibase.ext.spanner;
 */
package liquibase.ext.spanner.datatype;

import liquibase.database.Database;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.core.XMLType;
import liquibase.ext.spanner.CloudSpanner;
import liquibase.ext.spanner.ICloudSpanner;

/** XML is translated to STRING(MAX) as Cloud Spanner does not have a built-in type for XML. */
public class XmlTypeSpanner extends XMLType {
  private static final DatabaseDataType XML = new DatabaseDataType("STRING(MAX)");

  @Override
  public boolean supports(Database database) {
    return database instanceof ICloudSpanner;
  }

  @Override
  public DatabaseDataType toDatabaseDataType(Database database) {
    if (database instanceof ICloudSpanner) {
      return XML;
    } else {
      return super.toDatabaseDataType(database);
    }
  }

  @Override
  public int getPriority() {
    return PRIORITY_DATABASE;
  }
}
