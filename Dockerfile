FROM ubuntu:latest
LABEL authors="xallen"

ENTRYPOINT ["top", "-b"]