# Setup Docker image
FROM ruby:3.2.2-alpine3.18 AS setup

# Install dependencies
RUN apk update && apk --no-cache add \
    git \
    alpine-sdk

# Copy Gemfile
COPY Gemfile ${PWD}

# Install Ruby Gems
RUN bundle install

# =================================================================================================

# Base Docker image
FROM ruby:3.2.2-alpine3.18

# Metadata of Docker image
LABEL maintainer="maeda.naoki.md9@gmail.com"
LABEL version="1.0.0"

# Install dependencies
RUN apk update && apk --no-cache add \
    git

# Copy Ruby Gems directory
COPY --from=setup ${GEM_HOME} ${GEM_HOME}

