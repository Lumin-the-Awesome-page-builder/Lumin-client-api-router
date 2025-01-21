clojure -M -e "(compile 'deploy)"
bb -cp $(clojure -Spath) uberjar ServiceRouter.jar -m deploy
#java -jar -Dclojure.tools.logging.factory=clojure.tools.logging.impl/log4j2-factory ServiceRouter.jar