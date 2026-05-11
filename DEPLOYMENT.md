# Deployment Guide for Render.com

## Issue Fixed
✅ **Fixed MySQL Dialect Error**: Removed explicit H2Dialect configuration that was causing `Unknown table 'SEQUENCES' in information_schema` error when connecting to MySQL.

## How It Works Now
- Hibernate **auto-detects** the correct dialect based on the datasource connection
- When MySQL is configured → uses MySQL dialect automatically
- When no database is configured → falls back to in-memory H2 database

## Deployment Steps

### 1. Create MySQL Database on Render

1. Go to your Render dashboard
2. Click **"New +"** → **"PostgreSQL"** or use an external MySQL provider
3. **Note**: Render doesn't provide managed MySQL. You have two options:
   - **Option A**: Use Render PostgreSQL (recommended for Render)
   - **Option B**: Use external MySQL provider (e.g., PlanetScale, AWS RDS, Railway)

### 2. Configure Environment Variables

In your Render web service dashboard, set these environment variables:

#### Required Variables:
```
ACCESS_CODE_HASH=your_sha256_hash_here
PORT=10000
```

#### For MySQL Database (Option B):
```
SPRING_DATASOURCE_URL=jdbc:mysql://your-mysql-host:3306/augmind?createDatabaseIfNotExist=true&useSSL=true&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=your_mysql_username
SPRING_DATASOURCE_PASSWORD=your_mysql_password
```

#### For PostgreSQL Database (Option A - Recommended):
```
SPRING_DATASOURCE_URL=jdbc:postgresql://your-postgres-host:5432/augmind
SPRING_DATASOURCE_USERNAME=your_postgres_username
SPRING_DATASOURCE_PASSWORD=your_postgres_password
```

### 3. Deploy

1. Push your code to GitHub
2. Render will automatically build and deploy
3. Check the logs for successful startup
4. Access your app at the Render URL

## Testing Locally with Docker

### With MySQL:
```bash
docker-compose up --build
```
Access at: http://localhost:8080

### Without Database (H2 in-memory):
```bash
docker build -t augmind-app .
docker run -p 8080:8080 -e ACCESS_CODE_HASH=9a7ee9ac01d74171d10a12e586d6829e00078bd8275637704f35054e4b16cf8d augmind-app
```
Access at: http://localhost:8080

## Verification

### Health Check
```bash
curl http://your-app.onrender.com/health
```
Should return: `{"status":"UP"}`

### Access Page
Visit: `http://your-app.onrender.com/access`
- Should show the access code entry page
- Enter your access code to proceed

## Troubleshooting

### App shows "Render build logs"
- This means the app is still starting up
- Wait 30-60 seconds for the container to fully start
- Check Render logs for any errors

### Database Connection Errors
- Verify all database environment variables are set correctly
- Check that the database host is accessible from Render
- Ensure SSL settings match your database provider requirements

### App falls back to H2
- Check logs for: `"No datasource URL configured. Using in-memory H2 database."`
- This means `SPRING_DATASOURCE_URL` is not set or is invalid
- Set the correct database URL in Render environment variables

## Files Changed

1. **src/main/resources/application.properties**
   - Removed explicit H2Dialect configuration
   - Now allows Hibernate to auto-detect dialect

2. **render.yaml**
   - Added MySQL/PostgreSQL environment variable placeholders
   - Configured health check endpoint

## Notes

- All entities use `GenerationType.IDENTITY` (compatible with MySQL/PostgreSQL)
- No sequences are used (MySQL doesn't support sequences)
- Database schema is auto-created with `spring.jpa.hibernate.ddl-auto=update`
- Session timeout is 30 minutes
- Tests pass: 4 tests, 0 failures ✅
