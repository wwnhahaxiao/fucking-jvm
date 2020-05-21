package com.roshan.jvm;

/**
 * 测试对象回收机制
 * 当可达性分析中发现GCRoots无法到达的对象后,如果对象finalize方法被重写且没有被虚
 * 拟机调用过,会将对象加入f-queue队列中执行finalize方法,执行f-queue的线程优先级
 * 比较低,如果第二次标记时该对象仍然不可达或没来得及执行finalize,则标记为需要回收
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();
        //第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为执行finalize的线程优先度低,所以暂停500ms等待
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }
        //第二次 已经执行过finalize方法了 不会放入f-queue队列中,直接标记为待清除
        SAVE_HOOK = null;
        System.gc();
        //因为执行finalise的线程优先度低,所以暂停500ms等待
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }
    }
}
