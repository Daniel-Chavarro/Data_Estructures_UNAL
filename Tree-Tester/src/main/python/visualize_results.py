import pandas as pd
import matplotlib
matplotlib.use('Agg')  # Use non-interactive backend to avoid Tkinter issues
import matplotlib.pyplot as plt
import seaborn as sns
import os
from pathlib import Path

class VisualizeResults:
    def __init__(self):
        """
        Initialize the VisualizeResults class with data.
        
        Args:
            data: DataFrame containing the results to visualize
        """
        self.project_root = self.find_project_root(os.getcwd())
        self.data_tree = self.load_data(os.path.join(self.project_root, 'results', 'tree_benchmark_results.csv'))
        self.data_heap = self.load_data(os.path.join(self.project_root, 'results', 'heap_benchmark_results.csv'))

    
    def load_data(self, file_path):
        """
        Load data from CSV file
        
        Args:
            file_path: Path to the file
            
        Returns:
            DataFrame with the data
        """
        return pd.read_csv(file_path)

    def find_project_root(self, current_path, marker_files=('.git', 'pyproject.toml', 'setup.py', 'requirements.txt', 'README.md', 'Makefile', 'pom.xml')):

        """Find the project root by looking for marker files."""
        current = Path(current_path).absolute()
        

        for path in [current, *current.parents]:
            for marker in marker_files:
                if (path / marker).exists():
                    return path
                    

        return current
    
    def create_time_diagrams(self, data, title, output_dir, hue_column):
        """
        Create time diagrams for the given data
        
        Args:
            data: DataFrame containing the data
            title: Title for the plot
            output_dir: Directory to save the plot
            hue_column: Column to use for hue in the plot
        """
        os.makedirs(output_dir, exist_ok=True)

        sns.set_theme(style="whitegrid")

        benchmark_names = data['Benchmark'].unique()
        for benchmark in benchmark_names:
            benchmark_data = data[data['Benchmark'] == benchmark]
            
            plt.figure(figsize=(12, 8))
            chart = sns.lineplot(
                data=benchmark_data,
                x='DataSize',
                y='Score',
                hue=hue_column,
                markers=True,
                style=hue_column,
            )

            chart.set_title(f"{title} - {benchmark}")
            chart.set_xlabel("Size of the data structure")
            chart.set_ylabel("Average Time (in micro seconds)")
            chart.set_xscale('log')
            plt.tight_layout()
            
            # Save the plot instead of printing it
            output_file = os.path.join(output_dir, f"{benchmark.replace(' ', '_').lower()}_benchmark.png")
            plt.savefig(output_file, dpi=300, bbox_inches='tight')
            print(f"Saved plot: {output_file}")
            plt.close()

    def run(self):
        """
        Run the visualization process
        """
        print("Starting visualization process...")

        self.create_time_diagrams(
            self.data_tree,
            "Tree Benchmark Results",
            os.path.join(self.project_root, 'results', 'visualizations', 'tree'),
            'TreeType'
        )
        # Create combined column for heap visualization (HeapType + Degree)
        self.data_heap['HeapConfig'] = self.data_heap['HeapType'] + ' (Degree ' + self.data_heap['Degree'].astype(str) + ')'
        
        # Create time diagrams for heap data
        self.create_time_diagrams(
            self.data_heap,
            "Heap Benchmark Results",
            os.path.join(self.project_root, 'results', 'visualizations', 'heap'),
            'HeapConfig'
        )

        print("Visualization process completed.")


def main():
    """
    Main function to run the visualization
    """
    visualizer = VisualizeResults()
    visualizer.run()

if __name__ == "__main__":
    main()
    

