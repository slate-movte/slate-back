envpath_s='../config';

cd "docker_compose_local";

echo "hi";
docker volume create mysql;
docker compose --env-file "${envpath_s}/.env.properties" down;
docker compose --env-file "${envpath_s}/.env.properties" build --no-cache;
docker compose --env-file "${envpath_s}/.env.properties" up -d;
