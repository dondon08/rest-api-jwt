# rest-api-jwt
- database menggunakan MySQL 
- query ddl untuk create db dan create table ada di rest-api-jwt/test-jwt-directory.sql

# endpoint
- /auth/login : untuk mengautentikasi pengguna. AuthRequest username dan password. jika berhasil login token terbuat
- /api/jobs : membutuhkan token untuk mengakses enpoint ini
- /api/jobs/{id} : : membutuhkan token untuk mengakses enpoint ini
