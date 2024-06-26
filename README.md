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

```
brew install postgresql
brew services start postgresql@14
psql -d ivy_learn -c "CREATE USER postgres WITH PASSWORD 'password';"
psql -d ivy_learn -c "ALTER USER postgres WITH SUPERUSER;"
```

**Environment Variables**

```zshrc
export IVY_LEARN_DB_HOST="localhost"
export IVY_LEARN_DB_DATABASE="ivy_learn"
export IVY_LEARN_DB_USER="postgres"
export IVY_LEARN_DB_PORT="5432"
export IVY_LEARN_DB_PASSWORD="password"
export IVY_LEARN_GITHUB_PAT="your_github_personal_access_token"
```

**To run the sever:**

```
./scripts/runServer.sh
```