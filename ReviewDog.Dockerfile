# Setup Docker image
FROM alpine:3.20.2

# Metadata of Docker image
LABEL maintainer="maeda.naoki.md9@gmail.com"
LABEL version="1.0.0"

# Docker image build args
## ReviewDog args
### Installer URL
ARG REVIEWDOG_INSTALLER_DL_URL="https://raw.githubusercontent.com/reviewdog/reviewdog/master/install.sh"

### ReviewDog version
ARG REVIEWDOG_VERSION="0.18.0"

# Install dependencies
RUN apk update && apk --no-cache add \
    git=2.45.2-r0

# Install ReviewDog
SHELL ["/bin/ash", "-eo", "pipefail", "-c"]
RUN wget -O - -q ${REVIEWDOG_INSTALLER_DL_URL} | sh -s "v${REVIEWDOG_VERSION}"
