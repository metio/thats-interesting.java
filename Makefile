# http://www.gnu.org/software/make/manual/make.html
# https://www.gnu.org/prep/standards/html_node/Makefile-Basics.html#Makefile-Basics
# http://clarkgrubb.com/makefile-style-guide

############
# PROLOGUE #
############
MAKEFLAGS += --warn-undefined-variables
SHELL = /bin/bash
.SHELLFLAGS := -eu -o pipefail -c
.DEFAULT_GOAL := all
.DELETE_ON_ERROR:
.SUFFIXES:

######################
# INTERNAL VARIABLES #
######################
TIMESTAMPED_VERSION := $(shell /bin/date "+%Y.%m.%d-%H%M%S")
CURRENT_DATE := $(shell /bin/date "+%Y-%m-%d")
USERNAME := $(shell id -u -n)
USERID := $(shell id -u)
GREEN  := $(shell tput -Txterm setaf 2)
WHITE  := $(shell tput -Txterm setaf 7)
YELLOW := $(shell tput -Txterm setaf 3)
RESET  := $(shell tput -Txterm sgr0)

######################
# INTERNAL FUNCTIONS #
######################
HELP_FUN = \
    %help; \
    while(<>) { push @{$$help{$$2 // 'targets'}}, [$$1, $$3] if /^([a-zA-Z\-]+)\s*:.*\#\#(?:@([a-zA-Z\-]+))?\s(.*)$$/ }; \
    print "usage: make [target]\n\n"; \
    for (sort keys %help) { \
    print "${WHITE}$$_:${RESET}\n"; \
    for (@{$$help{$$_}}) { \
    $$sep = " " x (32 - length $$_->[0]); \
    print "  ${YELLOW}$$_->[0]${RESET}$$sep${GREEN}$$_->[1]${RESET}\n"; \
    }; \
    print "\n"; }

###############
# GOALS/RULES #
###############
.PHONY: all
all: help

help: ##@other Show this help
	@perl -e '$(HELP_FUN)' $(MAKEFILE_LIST)

.PHONY: install
install: ##@hacking Install all artifacts into local repository
	./mvnw clean install

.PHONY: test
test: ##@hacking Run all tests
	./mvnw test

.PHONY: sign-waiver
sign-waiver: ##@contributing Sign the WAIVER
	gpg2 --no-version --armor --sign AUTHORS/WAIVER
	mv AUTHORS/WAIVER.asc AUTHORS/WAIVER-signed-by-$(USERNAME)-$(CURRENT_DATE).asc

.PHONY: release-into-local-nexus
release-into-local-nexus: ##@release Release all artifacts into a local nexus
	./mvnw versions:set \
	   -DnewVersion=$(TIMESTAMPED_VERSION) \
	   -DgenerateBackupPoms=false
	-./mvnw clean deploy scm:tag \
	   -DpushChanges=false \
	   -DskipLocalStaging=true \
	   -Drelease=local
	./mvnw versions:set \
	   -DnewVersion=9999.99.99-SNAPSHOT \
	   -DgenerateBackupPoms=false

.PHONY: release-into-sonatype-nexus
release-into-sonatype-nexus: ##@release Release all artifacts into Maven Central (through Sonatype OSSRH)
	./mvnw versions:set \
	   -DnewVersion=$(TIMESTAMPED_VERSION) \
	   -DgenerateBackupPoms=false
	-./mvnw clean pgp:sign deploy scm:tag \
	   -DpushChanges=false \
	   -Drelease=sonatype
	-git push \
	   --tags \
	   origin master
	./mvnw versions:set \
	   -DnewVersion=9999.99.99-SNAPSHOT \
	   -DgenerateBackupPoms=false
