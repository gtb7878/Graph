//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

class BinHeap<P extends Comparable<? super P>, D> {
    private BinHeap.Entry<P, D>[] heap = new BinHeap.Entry[100];
    private int size = 0;

    public BinHeap() {
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(BinHeap.Entry<P, D> var1) {
        return var1 != null && this.heap[var1.index] == var1;
    }

    private int parent(int var1) {
        return (var1 + 1) / 2 - 1;
    }

    private int left(int var1) {
        return (var1 + 1) * 2 - 1;
    }

    private int right(int var1) {
        return (var1 + 1) * 2 + 1 - 1;
    }

    private void swap(int var1, int var2) {
        BinHeap.Entry var3 = this.heap[var1];
        this.heap[var1] = this.heap[var2];
        this.heap[var2] = var3;
        this.heap[var1].index = var1;
        this.heap[var2].index = var2;
    }

    private void lift(int var1) {
        int var2;
        while(var1 > 0 && ((Comparable)this.heap[var1].prio).compareTo(this.heap[var2 = this.parent(var1)].prio) < 0) {
            this.swap(var1, var2);
            var1 = var2;
        }

    }

    private void sink(int var1) {
        while(true) {
            int var2 = var1;
            int var3 = this.left(var1);
            int var4 = this.right(var1);
            if (var3 < this.size && ((Comparable)this.heap[var3].prio).compareTo(this.heap[var1].prio) < 0) {
                var2 = var3;
            }

            if (var4 < this.size && ((Comparable)this.heap[var4].prio).compareTo(this.heap[var2].prio) < 0) {
                var2 = var4;
            }

            if (var2 == var1) {
                return;
            }

            this.swap(var1, var2);
            var1 = var2;
        }
    }

    private void move(int var1) {
        BinHeap.Entry var2 = this.heap[var1];
        this.lift(var1);
        if (this.heap[var1] == var2) {
            this.sink(var1);
        }

    }

    public BinHeap.Entry<P, D> insert(P var1, D var2) {
        if (var1 == null) {
            return null;
        } else if (this.size == this.heap.length) {
            return null;
        } else {
            BinHeap.Entry var3 = this.heap[this.size] = new BinHeap.Entry(var1, var2, this.size);
            this.lift(this.size);
            ++this.size;
            return var3;
        }
    }

    public BinHeap.Entry<P, D> minimum() {
        return this.size == 0 ? null : this.heap[0];
    }

    public BinHeap.Entry<P, D> extractMin() {
        if (this.size == 0) {
            return null;
        } else {
            --this.size;
            this.swap(0, this.size);
            this.sink(0);

            BinHeap.Entry var1;
            try {
                var1 = this.heap[this.size];
            } finally {
                this.heap[this.size] = null;
            }

            return var1;
        }
    }

    public boolean changePrio(BinHeap.Entry<P, D> var1, P var2) {
        if (!this.contains(var1)) {
            return false;
        } else if (var2 == null) {
            return false;
        } else {
            var1.prio = var2;
            this.move(var1.index);
            return true;
        }
    }

    public boolean remove(BinHeap.Entry<P, D> var1) {
        if (!this.contains(var1)) {
            return false;
        } else {
            --this.size;
            int var2 = var1.index;
            this.swap(var2, this.size);
            this.move(var2);
            this.heap[this.size] = null;
            return true;
        }
    }

    private void dump(int var1, String var2) {
        BinHeap.Entry var3 = this.heap[var1];
        System.out.println(var2 + var3.prio() + " " + var3.data());
        var2 = var2 + "  ";
        int var4 = this.left(var1);
        int var5 = this.right(var1);
        if (var4 < this.size) {
            this.dump(var4, var2);
            if (var5 < this.size) {
                this.dump(var5, var2);
            }
        }

    }

    public void dump() {
        if (this.size > 0) {
            this.dump(0, "");
        }

    }

    public static class Entry<P, D> {
        private P prio;
        private D data;
        private int index;

        private Entry(P var1, D var2, int var3) {
            this.prio = var1;
            this.data = var2;
            this.index = var3;
        }

        public P prio() {
            return this.prio;
        }

        public D data() {
            return this.data;
        }
    }
}
