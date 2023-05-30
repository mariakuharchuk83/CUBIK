import matplotlib.pyplot as plt
import matplotlib.patches as patches


class Node:
    def __init__(self, lx, rx, left_son, right_son):
        self.lx = lx
        self.rx = rx
        self.left_son = left_son
        self.right_son = right_son
        self.node = None
        self.points = []


def build_tree(points, l_ind, r_ind, level) -> Node:
    left_x = points[l_ind][level]
    right_x = points[r_ind][level]
    new_node = Node(left_x, right_x, None, None)
    if level == len(points[0]) - 1:
        for i in range(0, r_ind - l_ind + 1):
            new_node.points.append(points[l_ind + i])
    elif level < len(points[0]) - 1:
        new_points = []
        for i in range(0, r_ind - l_ind + 1):
            new_points.append(points[l_ind + i])
        new_points = sorted(new_points, key=lambda x: x[level + 1])
        new_node.node = build_tree(new_points, 0, len(new_points) - 1, level + 1)
    if r_ind == l_ind + 1:
        new_node.left_son = build_tree(points, l_ind, l_ind, level)
        new_node.right_son = build_tree(points, r_ind, r_ind, level)
        return new_node
    if r_ind - l_ind > 0:
        m_ind = int((r_ind + l_ind) / 2)
        new_node.left_son = build_tree(points, l_ind, m_ind, level)
        new_node.right_son = build_tree(points, m_ind, r_ind, level)
    return new_node

def find_points_in_region(root, level, answer, region):
    if root is None:
        return
    l = root.lx
    r = root.rx
    if region[level][0] <= l and region[level][1] >= r:
        if root.node is None:
            for point in root.points:
                if region[0][0] <= point[0] <= region[0][1] and region[1][0] <= point[1] <= region[1][1]:
                    if point not in answer:  
                        answer.append(point)
        else:
            find_points_in_region(root.node, level + 1, answer, region)
    else:
        if region[level][0] <= r and region[level][1] >= l:
            find_points_in_region(root.left_son, level, answer, region)
            find_points_in_region(root.right_son, level, answer, region)
        elif region[level][1] < l:
            find_points_in_region(root.left_son, level, answer, region)
        elif region[level][0] > r:
            find_points_in_region(root.right_son, level, answer, region)



def read_points(file_name):
    points_list = []
    input_array = open(file_name).read().split()

    i = 0
    while i < len(input_array):
        points_list.append([float(input_array[i]), float(input_array[i + 1])])
        i += 2
    points_list = sorted(points_list, key=lambda x: x[0])  # sorted by x
    return points_list


def draw_points(points, region):
    fig, ax = plt.subplots(2)
    ax[0].set_ylim([-5, 15])
    ax[0].set_xlim([-5, 15])
    ax[1].set_xlim([-5, 15])
    ax[1].set_ylim([-5, 15])

    left = min(region[0])
    bottom = min(region[1])
    width = abs(region[0][1] - region[0][0])
    height = abs(region[1][1] - region[1][0])
    rect = patches.Rectangle((left, bottom), width, height, linewidth=1, edgecolor='r',
                             facecolor='none')
    ax[0].add_patch(rect)

    for point in points:
        circle = patches.Circle((point[0], point[1]), radius=0.08, color='b')
        ax[0].add_patch(circle)

    rect2 = patches.Rectangle((left, bottom), width, height, linewidth=1, edgecolor='r',
                             facecolor='none')

    ax[1].add_patch(rect2)
    answer = []
    tree = build_tree(points, 0, len(points) - 1, 0)
    find_points_in_region(tree, 0, answer, region)
    print(answer)
    for point in answer:
        circle = patches.Circle((point[0], point[1]), radius=0.08, color='g')
        ax[1].add_patch(circle)

    plt.show()


points = read_points("points.txt")
region = read_points("region.txt")
draw_points(points, region)