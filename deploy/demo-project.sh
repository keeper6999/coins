#!/bin/bash

NAME="coins"

PID_FILE="$NAME.pid"

COMMAND="java -jar coins-1.0-SNAPSHOT.jar"

case "$1" in
    start)
        echo "Starting $NAME ..."
        PID=$(${COMMAND} > /dev/null 2>&1 & echo $!)
        if [ -z ${PID} ]; then
                echo "Enable to start $NAME. No PID was created"
        else
                echo ${PID} > ${PID_FILE}
                echo "$NAME started"
        fi
        ;;

   status)
        if [ -f ${PID_FILE} ]; then
                PID=$(cat ${PID_FILE})
                if [ -z "$(ps aux | grep ${PID} | grep -v grep)" ]; then
                        echo "$NAME is dead but pid file exists"
                else
                        echo "$NAME is running"
                fi
        else
                echo "$NAME is not running"
        fi
        ;;

    stop)
        echo "Stopping $NAME ..."
        PID=$(cat ${PID_FILE})
        if [ -f ${PID_FILE} ]; then
                kill -9 ${PID}
                echo "$NAME is stopping"
                rm -f ${PID_FILE}
        else
                echo "$PID_FILE not found"
        fi
        ;;

    restart)
        $0 stop
        $0 start
        ;;

    *)
        echo "Usage $0 {status|start|stop|restart}"
        exit 1
        ;;
esac

exit 0