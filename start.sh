envpath='../config'
cd docker_compose_local
docker compose --env-file "${envpath}/.env.properties" down
docker compose --env-file "${envpath}/.env.properties" build --no-cache
docker compose --env-file "${envpath}/.env.properties" up -d