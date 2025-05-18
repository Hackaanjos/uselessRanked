const path = require('path');

module.exports = {
  default: {
    client: 'better-sqlite3',
    connection: {
      filename: path.resolve(__dirname, 'database.sqlite3'),
    },
    useNullAsDefault: true,
    migrations: {
      directory: path.resolve(__dirname, 'migrations'),
    },
  },
};
