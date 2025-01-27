package liquibase.ext.spanner.sqlgenerator;

import liquibase.database.Database;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.CreateDatabaseChangeLogLockTableGenerator;
import liquibase.statement.core.CreateDatabaseChangeLogLockTableStatement;
import liquibase.ext.spanner.ICloudSpanner;

public class CreateDatabaseChangeLogLockTableGeneratorSpanner
    extends CreateDatabaseChangeLogLockTableGenerator {
  final String createTableSQL =
      ""
          + "CREATE TABLE DATABASECHANGELOGLOCK\n"
          + "(\n"
          + "    id          int64,\n"
          + "    locked      bool,\n"
          + "    lockgranted timestamp,\n"
          + "    lockedby    string(max),\n"
          + ") primary key (id)";

  @Override
  public Sql[] generateSql(
      CreateDatabaseChangeLogLockTableStatement statement,
      Database database,
      SqlGeneratorChain sqlGeneratorChain) {
    return new Sql[] {new UnparsedSql(createTableSQL)};
  }

  @Override
  public int getPriority() {
    return PRIORITY_DATABASE;
  }

  @Override
  public boolean supports(CreateDatabaseChangeLogLockTableStatement statement, Database database) {
    return (database instanceof ICloudSpanner);
  }
}
