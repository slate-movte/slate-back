cd docker_compose_local
docker compose --env-file ../.env.yml down
docker compose --env-file ../.env.yml build --no-cache
docker compose --env-file ../.env.yml up -d