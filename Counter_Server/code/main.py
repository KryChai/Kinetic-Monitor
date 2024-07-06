import videoprocess as vp
import trainingsetprocess as tp
import videocapture as vc
import mysql.connector

if __name__ == '__main__':
    # 配置数据库连接
    config = {
        'user': 'root',  # 替换为您的数据库用户名
        'password': '123456',  # 替换为您的数据库密码
        'host': 'localhost',  # 数据库服务器地址
        'database': 'exercise',  # 数据库名
    }
    # 创建数据库连接
    conn = mysql.connector.connect(**config)
    # 创建游标对象
    cursor = conn.cursor()
    # 定义要插入的数据
    data_to_insert = (
        (1,1, 1, 30, '2023-01-01'),  # 这里的数据是示例，您需要替换为实际数据
        (1,2, 2, 45, '2023-01-02')
        # 添加更多要插入的数据...
    )

    # 编写SQL插入语句
    sql = """
    INSERT INTO exerciseRecord (userID, recordID, exerciseID, exerciseTime, exerciseDate)
    VALUES (%s, %s, %s, %s, %s)"""

    # 执行SQL语句
    cursor.executemany(sql, data_to_insert)

    # 提交事务
    conn.commit()

    # 关闭游标和连接
    cursor.close()
    conn.close()


    count=0
    while True:
        menu = int(input("请输入检测模式（数字）：1. 从本地导入视频检测\t2. 调用摄像头检测\t3. 退出\n"))
        if menu == 1:
            flag = int(input("请输入检测的运动类型（数字）：1. 俯卧撑\t2. 深蹲\t3. 引体向上\n"))
            video_path = input("请输入视频路径：")
            tp.trainset_process(flag)
            # 计数
            count=vp.video_process(video_path, flag)
            userID=123


            continue
        elif menu == 2:
            flag = int(input("请输入检测的运动类型（数字）：1. 俯卧撑\t2. 深蹲\t3. 引体向上\n"))
            print("\n按英文状态下的q或esc退出摄像头采集")
            tp.trainset_process(flag)
            vc.process(flag)
            continue
        elif menu == 3:
            break
        else:
            print("输入错误，请重新输入！")
            continue
