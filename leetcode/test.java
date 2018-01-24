＃Robot cleaner

The control API:

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean Move();

  // Robot will stay on the same cell after calling Turn*. k indicates how
  // many turns to perform.
  void TurnLeft(int k);
  void TurnRight(int k);

  // Clean the current cell.
  void Clean();

  boolean Move(Direction d);

}

ask the interviewer:
1. how can we know if we have clean all the cell in the room if we do not know the total size of the room.
2. can i use some data structure to store the visited coordinate so that i can know if there all the room is cleaned.
3. since the robot do not know the position of the room, so can i make an assumption that the start position of robot is 0,0
   and all the current position it moves is related to this 0,0
4. can i define a new class called Direction


public class RobotCleaner implements Robot{

	int currentpositionx = 0;
	int currentpositiony = 0;

	Stack<String> actions = new Stack<>();
	Set<String> visited = new HashSet<>();
	
        boolean MoveForward(){
            boolean success = Move();
            if(success) ++x;
        }

        boolean MoveLeft(){
            TurnLeft(1);
            boolean success = Move();
            if(success) --y;
            TurnRight(1);
            return success;
        }

        boolean MoveRight(){
            TurnRight(1);
            boolean success = Move();
            if(success) ++y;
            TurnLeft(1);
            return success;
        }

	boolean CheckandMove(Stirng Direction)
	{
			if(Direction.equals(Up))
			{
				return MoveUp();
			}

			else if(Direction.equals(Left))
			{
				return MoveLeft();
			}

			else 
			{
				return Moveright();
			}

	}


	public static main(String[] args){
		// input

		actions.push("Up");
		actions.push("Left");
		actions.push("Right");
		Clean();
		visited.add(x + "," + y);

		while(!actions.isEmpty())
		{
			String action = actions.pop();
			if(!CheckandMove())
			{
				continue;
			}
			if(!visited.contains(x + "," + "y"))
			{
				Clean();
				visited.add(x + "," + y);
				actions.push("Up");
				actions.push("Left");
				actions.push("Right");
			}
			else
			{
				if(action.equals("Up")) return Moveback();
				if(action.equals("left")) return Moveright();
				if(action.equals("right")) return Moveleft();

			}
					
		}
	}
}


给两个char array，其中有一些char为backspace就是删除前面的字符，要求输出一个boolean判断两个char array是否相同，时间要求O(n) , 空间要求O(1)
 例如：
 [a,b,'\b',d,c] 和[a,d,c] true
 [a,b,'\b','\b','\b',d,c] 和 [d,c] true
 [a,b,'\b','\b',d,c] 和 [d,c] true
 [a,b,d,'\b'] 和 [a,d] false


 public boolean check(char[] a, char[] b)
 {
 	// a is the array which has '/b'
 	// two pointer
 	int i = a.length() - 1, j = b.length() - 1;
 	int temp = 0;
 	int skip = 0;
 	while(i >= 0 && j >= -1)
 	{
 		if(a[i] == '/b')
 		{
 			skip += 1;
 		}	
 		else
 		{
 			if(i - skip >= 0 && j >= 0)
 			{
 				if(a[i - skip] == b[j])
 				{
 					i = i - skip - 1;
 					j--;
 					if(i < 0 && j < 0) return true;
 				}
 				else 
 					return false;
 				skip = 0;
 			}
 			else if(i - skip < 0 && j < 0)
 			{
 				return true;
 			}
 			else
 				return false;
 		}
 	}
 	return false;
 }


public class ManyTrees{
	int value;
	List<ManyTrees> childrenlist;

	public ManyTrees(int v, List<ManyTrees> c)
	{
		this.value = v;
		this.childrenlist = c;
	}
}

int max = 0;
public int findmaxlength(ManyTrees root)
{
	if(root == null) return 0;
	helper(root);
	return max;
}

public int helper(ManyTrees root)
{
	if(root == null) return 0;
	if(root.childrenlist == null) return 1;
	int temp = 0;
	// since it is a tree, so there is no possible that there has a cycle, we do not need a visited array to check if there is cycle
	for(ManyTrees child: root.childrenlist)
	{
		temp = Math.max(temp, helper(child)) + 1;
	}
	max = Math.max(temp, max);
	return temp;
}

Follow up: add a happiness to every manytree nodes

public class ManyTrees{
	int value;
	List<ManyTrees> childrenlist;
	double happiness;

	public ManyTrees(int v, List<ManyTrees> c, double h)
	{
		this.value = v;
		this.childrenlist = c;
		this.happiness = h;
	}
}

public class Result{
	// have how may employees
	int number;
	// the sum of the happiness they have
	double happiness;

	public Result(int n, double h)
	{
		this.number = n;
		this.happiness = h;
	}
}

int max = 0;
public void caculatehappiness(ManyTrees root)
{
	if(root == null) return 0;
	helper(root);
}

public Result helper(ManyTrees root)
{
	Result r = new Result();
	if(root == null) return r;
	if(root.childrenlist == null) 
	{
		r.number = 1;
		r.happiness = root.value;
		return r;
	}

	int totalnumber = 0;
	int sum = 0;
	double avg = 0.0;
	for(ManyTrees child: root.childrenlist)
	{
		Result temp = helper(child);
		totalnumber += temp.number;
		sum += temp.happiness;
	}
	avg = (double)sum / totalnumber;
	root.happiness = avg + root.value;
	r.number = totalnumber + 1;
	r.happiness = sum;
	return r;
}


题目很简单， 两个List， 里面的element 可以是任意type。返回 两个list的差。

比如 A=【1,2,2,2】
B= 【1,2,3,3,3】
A-B= 【2,2】
B-A =【3,3,3】
要求就是 求出 A-B 和 B-A。

O(m) + O(n) + O(result)
public List<Integer> sub(List<Integer> A, List<Integer> B)
{
	//A-B
	List <Integer> result = new ArrayList<Integer>();
	HashMap<Integer, Integer> map = new HashMap<>();
	for(int i : A)
	{
		if(!map.containsKey(i))
			map.put(i, 1);
		else
			map.put(i, map.get(i) + 1);
	}

	for(int j : B)
	{
		if(map.containsKey(j))
		{
			// there is no such element in A
			if(map.get(j) == 0)
				map.remove(j);
			map.put(j, map.get(j) - 1);
		}
	}

	for(int key : map.keySet())
	{
		for(int i = 0; i < map.get(key); i++)
		{
			result.add(key);
		}
	}
}

// brute force
public List<Integer> sub(List<Integer> A, List<Integer> B)
{
	List <Integer> result = new ArrayList<Integer>();
	boolean[] visited = new boolean[B.size()]
	for(int i: A)
	{
		for(int j : B)
		{
			if(i == j)
			{
				B.remove()
			}
				break;
			result.add(j);
		}
	}
}

public boolean check(String s)
{
	int len = s.length();
	if(len == 1) return true;
	boolean result = checkValid(s);
	if(!result) return false;
	
	List<String> substring = new ArrayList<>();
	for(int i = 0; i < len; i++)
	{
		substring.add(s.substring(0,i) + s.substring(i + 1,len));
	}

	boolean t = false;
	for(String sub: substring)
	{
		if(check(sub))
			t = true;
	}

	return t;
}

给一个integer list in ascending order，把每个数都平方，返回排好序的平方数组。例子：[-2, -1, 0, 2]，返回[0, 1, 4, 4]。
follow up: 而是y = x^2 + a*x，再返回按这个y值排好序的数组

public static int[] squarenum(int[] nums)
{
	int[] result = new int[nums.length];
	if(nums.length == 0 || nums == null)
		return null;
	if(nums[0] >= 0)
	{
		int index = 0;
		for(int i = 0; i < nums.length; i++)
		{
			result[index] = nums[i] * nums[i];
		}
	}
	else
	{
		int k = nums.length - 1;
		int low = 0;
		int high = nums.length - 1;
		while(low <= high)
		{
			if(Math.abs(nums[low]) < nums[high])
			{
				result[k] = nums[high] * nums[high];
				high--;
				k--;
			}
			else
			{
				result[k] = nums[low] * nums[low]
				k--;
				low++;
			}
		}
	}
	
}








