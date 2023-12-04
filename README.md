# Nova Sales API

## Requirements


## How to run

### Docker

Run the following command to start the database container:
```bash
dokcer-compose up --build
```

get the name of the container:
```bash
docker ps
```

go into the terminal of the container:
```bash
docker exec -it sales-postgres-1 bash
```

run the following command to open postgres interface:
```bash
psql -U postgres
```

create the database:
```bash
CREATE DATABASE nova_sales;
```

close the postgres interface:
```bash
\q
```
close the container terminal:
```bash
exit
```

For the further usiage of the container, use: 
```bash
docker-compose up
```