#!/bin/bash

bash < <(curl -s https://raw.githubusercontent.com/babashka/babashka/master/install)
curl -L -O https://github.com/clojure/brew-install/releases/latest/download/posix-install.sh
chmod +x posix-install.sh
./posix-install.sh

sleep 36000