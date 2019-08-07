#include <iostream>
#include <windows.h>

using namespace std;

void writeReslutToLog(){
    string command="adb logcat CrackMain:D *:S *:W *:E *:F *:S > C:\\Users\\yukar1z0e\\Desktop\\log.logs";
    cout<<command.c_str()<<endl;
    WinExec(command.c_str(),1);
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

int main() {
    while (true)
    {
        string phoneNumber;
        cout << "输入手机号" << endl;
        cin >> phoneNumber;
        //writeReslutToLog();
        if(phoneNumber=="0"){
            break;
        }
        search(phoneNumber);
    }
    cout<<"查询结束"<<endl;
    return 0;
}