import os
import re

# Define the base directory for your output
output_dir = "composeApp/build/dist/js/productionExecutable"
input_file = os.path.join(output_dir, "index.html")

# Define the routes to pre-render
routes = {
    "lesson": "https://ivylearn.app/lesson",
    "course": "https://ivylearn.app/course"
}

# Check if the input file exists
if not os.path.exists(input_file):
    raise FileNotFoundError(f"The file {input_file} does not exist. Make sure the build process has completed.")

# Read the base HTML template from the production executable
with open(input_file, "r", encoding="utf-8") as f:
    base_html = f.read()


# Function to modify the `og:url` tag using regex
def modify_og_url(html_content, new_url):
    """Modify the og:url meta tag in the given HTML content."""
    # Use regex to find the og:url meta tag and replace its content
    return re.sub(
        r'<meta property="og:url" content=".*?">',
        f'<meta property="og:url" content="{new_url}">',
        html_content
    )


# Generate pre-rendered HTML files for each route
for route, og_url in routes.items():
    route_dir = os.path.join(output_dir, route)
    os.makedirs(route_dir, exist_ok=True)  # Ensure the directory exists

    # Modify the HTML content with the new og:url
    modified_html = modify_og_url(base_html, og_url)

    # Write the modified HTML to the appropriate file
    output_file = os.path.join(route_dir, "index.html")
    with open(output_file, "w", encoding="utf-8") as f:
        f.write(modified_html)
        print(f"Pre-rendered {og_url} at {route_dir}")

print("Pre-rendering complete!")
