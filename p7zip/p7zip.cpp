extern "C" int p7zipShell(char *cmd) {
    int numArgs;
    // 最大支持16个参数
    char temp[16][512] = {0};
    numArgs = parseCmd(cmd, temp);
    char *args[16] = {0};
    for (int i = 0; i < numArgs; ++i) {
        args[i] = temp[i];
    }
    return main(numArgs, args);
}

static int parseCmd(char *cmd, char argv[16][512]) {
    int size = strlen(cmd);
    int preChar = 0;
    int a = 0;
    int b = 0;
    for (int i = 0; i < size; ++i) {
        char c = cmd[i];
        switch (c) {
            case ' ':
            case '\t':
                if (preChar == 1) {
                    argv[a][b++] = '\0';
                    a++;
                    b = 0;
                    preChar = 0;
                }
                break;

            default:
                preChar = 1;
                argv[a][b++] = c;
                break;
        }
    }

    if (cmd[size - 1] != ' ' && cmd[size - 1] != '\t') {
        argv[a][b] = '\0';
        a++;
    }
    return a;
}

#include <string.h>
#include "C/7zTypes.h"

extern int MY_CDECL main
(
        int numArgs, char *args[]
);