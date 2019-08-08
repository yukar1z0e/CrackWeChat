#include <iostream>
#include <windows.h>

using namespace std;

void writeReslutToLog(){
    string command = R"(adb logcat CrackMain:D *:S *:W *:E *:F *:S -f /data/local/tmp/logs.txt)";
    //cout<<command.c_str()<<endl;
    WinExec(command.c_str(),1);
}

void pullReslut() {
    string command = R"(adb pull /data/local/tmp/logs.txt C:\Users\yukar1z0e\Desktop)";
    system(command.c_str());
}

void getResult() {
    FILE *fid = fopen("C:/Users/yukar1z0e/Desktop/logs.txt", "r");
    char line[2048];
    memset(line, 0, 2048);
    while (!feof(fid)) {
        fgets(line, 2048, fid);
        cout << line << endl;
    }
}

void search(string phoneNumber) {
    string command;
    command = "adb shell input text " + phoneNumber;
    system(command.c_str());
    command="adb shell input tap 710 205";
    cout << "查询中" << endl;
    system(command.c_str());
    command="adb shell input tap 710 95";
    cout << "查询完成" << endl;
    system(command.c_str());
    cout<<"继续查询"<<endl;
}

void readFile() {
    FILE *fid = fopen("C:/Users/yukar1z0e/Desktop/telegram.txt", "r");
    char line[1024];
    memset(line, 0, 1024);
    while (!feof(fid)) {
        fgets(line, 1024, fid);
        cout << line << endl;
    }
}

int main() {
    FILE *fid = fopen("C:/Users/yukar1z0e/Desktop/telegram.txt", "r");
    char line[1024];
    memset(line, 0, 1024);
    writeReslutToLog();
    while (!feof(fid)) {
        fgets(line, 1024, fid);
        string phoneNumber = line;
        if (phoneNumber == "0") {
            break;
        }
        search(phoneNumber);
    }
    pullReslut();
    getResult();
    cout << "查询结束" << endl;
    return 0;
}


/*int main() {
    while (true)
    {
        string phoneNumber;
        cout << "输入手机号" << endl;
        cin >> phoneNumber;
        writeReslutToLog();
        if(phoneNumber=="0"){
            break;
        }
        search(phoneNumber);
        pullReslut();
        getResult();
    }
    cout<<"查询结束"<<endl;
    return 0;
}*/