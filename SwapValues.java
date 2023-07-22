public class SwapValues
{
    public static void main(String[] args) 
    {
        int[] values = { 10, 20 };

        System.out.println("Before swapping: x = " + values[0] + ", y = " + values[1]);

        swapValues(values);

        System.out.println("After swapping: x = " + values[0] + ", y = " + values[1]);
    }

    public static void swapValues(int[] arr) 
    {
        int temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
    }
}

