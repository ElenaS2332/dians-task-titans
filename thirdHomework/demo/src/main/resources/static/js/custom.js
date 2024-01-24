function toggleDesc(truncatedDescription) {
    if (truncatedDescription) {
        // If truncated, make it not truncated
        document.querySelector('.description-text').textContent = /*[[${winery.description}]]*/ '';
    } else {
        // If not truncated, make it truncated
        document.querySelector('.description-text').textContent = /*[[${winery.truncatedDescription}]]*/ '';
    }
}