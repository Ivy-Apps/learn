# Ivy Learn

Learn programming by thinking.

**To run the web app:**

```
./scripts/runWebApp.sh
```

**To run the desktop app:**

```
./scripts/runDesktopApp.sh
```

## Server

You need to have a PostgreSQL database.

```zshrc
brew install postgresql
brew services start postgresql@14
psql -U postgres -c "CREATE DATABASE ivy_learn;"
psql -d ivy_learn -c "CREATE USER postgres WITH PASSWORD 'password';"
psql -d ivy_learn -c "ALTER USER postgres WITH SUPERUSER;"
```

**(optional) Drop local database:**

```zshrc
psql -U postgres -c "DROP DATABASE ivy_learn;"
psql -U postgres -c "CREATE DATABASE ivy_learn;"
```

**Environment Variables**

```zshrc
export IVY_LEARN_DB_HOST="localhost"
export IVY_LEARN_DB_DATABASE="ivy_learn"
export IVY_LEARN_DB_USER="postgres"
export IVY_LEARN_DB_PORT="5432"
export IVY_LEARN_DB_PASSWORD="password"
export IVY_LEARN_GITHUB_PAT="your_github_personal_access_token"

export IVY_GOOGLE_OAUTH_CLIENT_ID="your-client-id"
export IVY_GOOGLE_OAUTH_CLIENT_SECRET="your-client-secret"
```

**To run the sever:**

```
./scripts/runServer.sh
```