cd docker_compose_local
docker compose down
docker compose --env-file ../.env build --no-cache
docker compose --env-file ../.env up -d