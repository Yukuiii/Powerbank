const baseUrl = 'localhoust:8080/v1';

/**
 * 封装的异步 fetch 请求函数
 * @param {string} endpoint - 请求的端点（不包括基础 URL）
 * @param {object} options - 请求的选项，例如方法、头信息、请求体等
 * @returns {Promise} - 返回一个 Promise，解析为响应数据
 */
async function fetchRequest(endpoint, options = {}) {
    const url = `${baseUrl}${endpoint}`;

    try {
        const response = await fetch(url, {
            method: options.method || 'GET', // 默认使用 GET 方法
            headers: {
                'Content-Type': 'application/json',
                ...options.headers
            },
            body: JSON.stringify(options.body) || null
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const contentType = response.headers.get('Content-Type');
        if (contentType && contentType.includes('application/json')) {
            return await response.json();
        } else {
            return await response
        }
    } catch (error) {
        console.error('Fetch error:', error);
        throw error;
    }
}

export default fetchRequest;