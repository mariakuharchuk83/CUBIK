import read
import plot
import algorythm


vertexes_arr = read.read_vertexes("vertexes.txt")
edges_arr = read.read_edges("edges.txt", vertexes_arr)
point = read.read_point("point.txt")
#print("Work")
vertexes_arr = sorted(vertexes_arr, key=lambda p: p.y)

v_size = len(vertexes_arr)

edges_in = []
edges_out = []

for i in range(v_size):
    edges_in.append([])
    edges_out.append([])

for e in edges_arr:
    e.weight = 1

    start_i = vertexes_arr.index(e.start)
    end_i = vertexes_arr.index(e.end)

    edges_in[end_i].append(e)
    edges_out[start_i].append(e)

algorythm.balance_up(vertexes_arr, edges_in, edges_out)
algorythm.balance_down(vertexes_arr, edges_in, edges_out)

plot.plot(vertexes_arr, edges_arr, point)

num_chains = algorythm.sum_weight(edges_out[0])

ordered_out = []
for v in edges_out:
    v = algorythm.sort_edges(v)
    ordered_out.append(v)

chains = algorythm.create_chains(vertexes_arr, ordered_out)

for i, chain in enumerate(chains):
    print(f"Chain {i}: {vertexes_arr.index(chain[0].start)}", end="")
    for edge in chain:
        print(f" {vertexes_arr.index(edge.end)}", end="")
    print()

result = algorythm.find(point, chains)
result_len = len(result)

#print(f"Lenth:{result_len}")

if result_len != 0:
    if result_len == 1:
        first = result[0]
        print(f"Point is on chain {first} on edge {algorythm.find_on_edge(point, chains[first])}")
    else:
        first, second = result
        print(f"Point is between chains {first} and {second}"
              f" and the closest edge is {algorythm.find_closest_edge(point, chains[first], chains[second])}")
else:
    print("Point is not inside graph")
