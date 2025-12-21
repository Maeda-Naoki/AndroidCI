# Setup Docker image
FROM alpine:3.23.2@sha256:865b95f46d98cf867a156fe4a135ad3fe50d2056aa3f25ed31662dff6da4eb62

# Metadata of Docker image
LABEL maintainer="maeda.naoki.md9@gmail.com"
LABEL version="1.0.0"

# Docker image build args
## ReviewDog args
### Installer URL
ARG REVIEWDOG_INSTALLER_DL_URL="https://raw.githubusercontent.com/reviewdog/reviewdog/fd59714416d6d9a1c0692d872e38e7f8448df4fc/install.sh"

### ReviewDog version
ARG REVIEWDOG_VERSION="0.21.0"

# Install dependencies
RUN apk update && apk --no-cache add \
    git=2.52.0-r0

# Install ReviewDog
SHELL ["/bin/ash", "-eo", "pipefail", "-c"]
RUN wget -O - -q ${REVIEWDOG_INSTALLER_DL_URL} | sh -s "v${REVIEWDOG_VERSION}"
