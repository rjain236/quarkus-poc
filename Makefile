SHELL=/bin/bash
SCRIPT_PATH=scripts
TOUCH_RESULT:=$(shell touch ${SCRIPT_PATH}/envs)

include ${SCRIPT_PATH}/envs
export $(shell sed 's/=.*//' $(SCRIPT_PATH)/envs)

.PHONY: clean_env
clean_env:
	@rm $(SCRIPT_PATH)/envs
	@touch $(SCRIPT_PATH)/envs

.PHONY: set_env
set_env: clean_env
	@./$(SCRIPT_PATH)/set_env.sh
	export $(shell sed 's/=.*//' $(SCRIPT_PATH)/envs)

.PHONY: build
build:
	@./$(SCRIPT_PATH)/build_images.sh

.PHONY: publish
publish:
	@./$(SCRIPT_PATH)/publish_images.sh

.PHONY: compile
compile:
	@./$(SCRIPT_PATH)/compile_and_test.sh

.PHONY: all
all: set_env compile build publish



