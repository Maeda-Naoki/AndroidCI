# Setup Docker image
FROM ruby:3.2.2-slim-bookworm AS setup

# Install dependencies
RUN apt-get update && apt-get install -y --no-install-recommends \
    git \
    build-essential

# Copy Gemfile
COPY Gemfile ${PWD}

# Install Ruby Gems
RUN bundle install

# =================================================================================================

# Base Docker image
FROM ruby:3.2.2-slim-bookworm

